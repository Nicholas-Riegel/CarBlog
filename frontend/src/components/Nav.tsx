import { Link } from "react-router";
import styles from './Nav.module.css'

const Nav = () => {
  return (
    <nav>
      <Link className={styles['home']} to="/">Home</Link>
      <Link to="/add-car">Add Car</Link>
    </nav>
  );
};

export default Nav;