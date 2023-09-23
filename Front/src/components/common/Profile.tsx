type ProfileName = string;
type ProfileImage = string;

interface ProfileProps {
  profileName: ProfileName;
  profileImage?: ProfileImage;
  size?: "xsmall"|"small" | "medium" | "large";
}

const ProfileSize = {
  xsmall: 50,
  small : 60,
  medium : 80,
  large : 120
}

const Profile = ({ profileName, profileImage, size = "large" }: ProfileProps) => {
  return (
    <div className="flex flex-col items-center justify-around w-full">
      <img
        className={`w-[${ProfileSize[size]}px] h-[${ProfileSize[size]}px] rounded-full`}
        src={profileImage}
        alt="프로필 이미지"
      />
      <p className="text-main font-strong text-m">{profileName}</p>
    </div>
  );
};

export default Profile;
