type Nickname = string;
type Joined = boolean;
type UserId = number;
type Email = string;
type RefreshToken = string;
type AccessToken = string;
type Role = "CHILD" | "PARENT";

export type UserData = {
  nickname: Nickname;
  joined: Joined;
  userId: UserId;
  email: Email;
  refreshToken: RefreshToken;
  accessToken: AccessToken;
  role: Role;
};
