import Profile from "./Profile";
import defaultImg from "../../assets/imgs/signup/smile.png";

const tempChildren = [
  {
    name: "child1",
    image: "https://images.pexels.com/photos/140134/",
  },
  {
    name: "child2",
    image: "https://images.pexels.com/photos/140134/",
  },
  {
    name: "child3",
    image: "https://images.pexels.com/photos/140134/",
  },
  {
    name: "child2",
    image: "https://images.pexels.com/photos/140134/",
  },
  {
    name: "child3",
    image: "https://images.pexels.com/photos/140134/",
  },
  {
    name: "child3",
    image: "https://images.pexels.com/photos/140134/",
  },
  {
    name: "child3",
    image: "https://images.pexels.com/photos/140134/",
  },
];

function TopChildList() {
  return (
    <div className="">
        <div className="flex mx-auto overflow-x-auto no-scrollbar">
          {tempChildren.map((child, index) => {
            return (
              <div className="flex-none mx-6">
                <Profile
                  profileName={child.name}
                  profileImage={defaultImg}
                  size="xsmall"
                  key={index}
                />
              </div>
            );
          })}
        </div>
    </div>
  );
}

export default TopChildList;
