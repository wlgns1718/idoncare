type AccessToken = string | null;
type Email = string;
type Joined = boolean;
type Msg = string;
type Nickname = string;
type RefreshToken = string | null;
type UserId = string;
type Role = string;
type Info = null | PostLoginInfo;
type Error = string | null;
type Code = number;
type Headers = PostLoginHeaders;

interface PostLoginHeaders {
  authorization: string;
}

interface PostLoginInfo {
  accessToken: AccessToken;
  email: Email;
  joined: Joined;
  msg: Msg;
  nickname: Nickname;
  refreshToken: RefreshToken;
  userId: UserId;
  role: Role;
}

export interface PostLogin {
  info: Info;
  error: Error;
  code: Code;
  headers: Headers;
}
