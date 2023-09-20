type Type = string;
type Name = string;
type Nickname = string;
type Birth = string;
type Email = string;
type Address = string;
type Phone = string;
type Code = string;
type HasAccount = string;

export interface SignupUserInfo {
  type?: Type;
  name?: Name;
  nickname?: Nickname;
  birth?: Birth;
  email?: Email;
  address?: Address;
  phone?: Phone;
  code?: Code;
  hasAccount?: HasAccount;
}
