import Profile from "../common/Profile";

const SignupTypeSelect = () => {
  return (
    <>
      <div>
        <Profile
          profileName="자식"
          profileImage="https://media.istockphoto.com/id/1338134336/photo/headshot-portrait-african-30s-man-smile-look-at-camera.webp?b=1&s=170667a&w=0&k=20&c=j-oMdWCMLx5rIx-_W33o3q3aW9CiAWEvv9XrJQ3fTMU="
        />
        <p>용돈을 받을래요!</p>
      </div>
      <div>
        <Profile
          profileName="부모"
          profileImage="https://shotkit.com/wp-content/uploads/bb-plugin/cache/cool-profile-pic-matheus-ferrero-landscape-6cbeea07ce870fc53bedd94909941a4b-zybravgx2q47.jpeg"
        />
        <p>용돈을 줄래요!</p>
      </div>
    </>
  );
};

export default SignupTypeSelect;
