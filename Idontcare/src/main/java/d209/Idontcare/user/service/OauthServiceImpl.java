package d209.Idontcare.user.service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import d209.Idontcare.TUser;
import d209.Idontcare.common.exception.NoSuchUserException;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{
    private final UserService userService;

    private static final Long ACCESS_EXPIRED = 2000 * 60 * 60L; //2시간
    private static final Long REFRESH_EXPIRED = 1000 * 60 * 60 * 24 * 7L; //7일

    //properties분리해서 숨겨줄 값들
    private static final String REST_API_KEY = "57207a98af7edacf30bb14f2b4effbc4";
    private static final String REDIRECT_URL = "http://127.0.0.1:8000";
    private static final String CLIENT_SECRET = "Srvk6ZfqwnWw6bDf2tZVA4I9VP731p3D";

    @Override
//    public String getOauthAccessToken(String code)throws IOException, NullPointerException, Exception{
    public String getOauthAccessToken(String code)throws Exception{
        //인가 코드를 통해 Oauth에서 AccessToken 얻기
        String accessToken = "";
        HttpURLConnection conn = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        StringBuilder sb = new StringBuilder();

        int connTimeout = 5000;
        int readTimeout = 3000;

        String apiUrl = "https://kauth.kakao.com/oauth/token";
        URL url = new URL(apiUrl);

        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST"); // 요청방식 설정
        conn.setDoOutput(true); //OutputStream으로 POST 데이터를 넘겨주겠다는 옵션 디폴트는 false
        //Request Header값 셋팅 setRequestProperty(String key, String value)
        conn.setConnectTimeout(connTimeout); //서버 접속시 연결 시간
        conn.setReadTimeout(readTimeout); //Read시 연결 시간

        bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        //x-www-form-urlencoded;charset=utf-8 방식의 body 생성

        sb.append("grant_type=authorization_code");
        sb.append("&client_id="+REST_API_KEY);//REST API KEY
        sb.append("&redirect_uri="+REDIRECT_URL);
        sb.append("&code=").append(code);
        sb.append("&client_secret="+CLIENT_SECRET);
//        OutputStream outStream = conn.getOutputStream();

        bw.write(sb.toString());
        bw.flush();
        int responseCode = conn.getResponseCode();
        System.out.println(responseCode);
       if(responseCode != 200){
           System.out.println("ERRDR");
           throw new Exception();
           //커스텀 에러 발생
       }
       // Response 값 받아오기

        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String temp = "";
        sb.setLength(0);

       while((temp = br.readLine()) != null){
          sb.append(temp);
       }
       JsonObject json = (JsonObject) JsonParser.parseString(sb.toString());


        System.out.println("json: " + json);
        accessToken = json.get("access_token").getAsString();

       bw.close();
       br.close();

       return accessToken;

    }

    @Override
    public Map<String, Object> getUserInfo(String accessToken) throws IOException{
        Map<String,Object> userInfo = new HashMap<>();

        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        URL url = new URL(reqUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        int responseCode = conn.getResponseCode();
        System.out.println("responseCode : " + responseCode);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));


        String line = "";
        StringBuilder result = new StringBuilder();
        while((line = br.readLine())!= null){
            result.append(line);
        }
//        System.out.println("response body : " + result.toString());

        JsonObject json = (JsonObject) JsonParser.parseString(result.toString());
        Long id = Long.parseLong(json.get("id").getAsString());
        JsonObject kakaoAccount = (JsonObject) JsonParser.parseString(json.get("kakao_account").toString());
        String email = "";
        String nickname = "";
//        if(kakaoAccount.has("profile")){
//            //닉네임 사용에 허용한 경우
//            nickname = ((JsonObject) kakaoAccount.get("profile")).get("nickname").toString();
////            System.out.println("nickname = " + nickname);
//        }
//
//        if(kakaoAccount.has("email")){
//            //이메일 사용에 허용한 경우
//            email = kakaoAccount.get("email").getAsString();
////            System.out.println(email);
//        }
        Optional<User> user = userService.findByUserId(id);


        if(user.isEmpty()){
            System.out.println("등록된 유저가 아닙니다.");
//            System.out.println("nickname : " + nickname );
//            System.out.println("email : " + email);
            userInfo.put("msg","회원정보가 없습니다. 회원가입 페이지로 이동합니다.");
            userInfo.put("id",id);
            userInfo.put("boolean", false);
//            userInfo.put("nickname",nickname);
//            userInfo.put("email",email);
        }
        else{

            System.out.println("이우철 싸패");

            String jwtAccessToken = JwtUtil.createToken(String.valueOf(id),ACCESS_EXPIRED);
            String jwtRefreshToken = JwtUtil.createToken(String.valueOf(id),REFRESH_EXPIRED);

            userInfo.put("id",id);

            userInfo.put("refreshToken",jwtRefreshToken);
            userInfo.put("accessToken",jwtAccessToken);
            user.get().setRefreshToken(jwtRefreshToken);
            userInfo.put("msg","등록된 회원입니다.");
            userInfo.put("boolean", true);
            System.out.println("등록된 회원입니다.");
        }
        return userInfo;
    }




}
