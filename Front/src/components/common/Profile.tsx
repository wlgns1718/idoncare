type ProfileName = string;
type ProfileImage = string;

interface ProfileProps {
  profileName: ProfileName;
  profileImage?: ProfileImage;
}

const Profile = ({ profileName, profileImage }: ProfileProps) => {
  return (
    <div className="flex flex-col items-center justify-around w-full">
      <img className="w-[120px] h-[120px] rounded-full" src={profileImage} alt="프로필 이미지" />
      <p className="text-main font-strong text-m">{profileName}</p>
    </div>
  );
};

export default Profile;
