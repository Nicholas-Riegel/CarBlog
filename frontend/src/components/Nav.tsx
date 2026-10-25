import { Link } from "react-router";

const Nav = () => {
  return (
    <nav>
      <Link to="/">Home</Link>
      <Link to="/add-car">Add Car</Link>
    </nav>
  );
};

export default Nav;