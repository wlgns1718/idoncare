type Nickname = string | null;
type Password = string | null;
type Name = string | null;
type Phone = string | null;
type Birth = string | null;
type Email = string | null;
type Type = string | null;
type PayPassword = string | null;
type BankCode = string | null;
type Account = string | null;
type SeqNo = string | null;

export interface SignupUserInfo {
  nickname: Nickname;
  name: Name;
  password: Password;
  phone: Phone;
  birth: Birth;
  email: Email;
  type: Type;
  payPassword: PayPassword;
  bankCode: BankCode;
  account: Account;
  seqNo: SeqNo;
}
