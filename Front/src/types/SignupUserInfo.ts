type Nickname = string;
type Password = string;
type Name = string;
type Phone = string;
type Birth = string;
type Email = string;
type Type = string;
type PayPassword = string;
type BankCode = string;
type Account = string;
type SeqNo = string;

export interface SignupUserInfo {
  nickname?: Nickname;
  name?: Name;
  password?: Password;
  phone?: Phone;
  birth?: Birth;
  email?: Email;
  type?: Type;
  payPassword?: PayPassword;
  bankCode?: BankCode;
  account?: Account;
  seqNo?: SeqNo;
}
