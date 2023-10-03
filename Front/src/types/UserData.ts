type Nickname = string | null;
type Joined = boolean;
type UserId = string | null;
type Email = string | null;
type RefreshToken = string | null;
type AccessToken = string | null;
type Role = string | null;

export type UserData = {
  nickname: Nickname;
  joined: Joined;
  userId: UserId;
  email: Email;
  refreshToken: RefreshToken;
  accessToken: AccessToken;
  role: Role;
};
