
import { Link, NavLink, useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext.jsx'
export default function Navbar(){
  const nav = useNavigate()
  const { token, roles, logout } = useAuth()
  return (
    <div className="navbar">
      <div className="navbar-inner container">
        <Link to="/" className="brand">🎓 E-Learn</Link>
        <div className="row">
          <NavLink to="/assigned">My Courses</NavLink>
          <NavLink to="/courses">Catalog</NavLink>
          {roles?.includes('INSTRUCTOR') && <NavLink to="/instructor">Instructor</NavLink>}
          {roles?.includes('ADMIN') && <NavLink to="/admin">Admin</NavLink>}
        </div>
        <div className="row">
          {!token ? (<><NavLink to="/login">Login</NavLink><NavLink to="/register">Register</NavLink></>) : (<button className="button" onClick={()=>{ logout(); nav('/login') }}>Logout</button>)}
        </div>
      </div>
    </div>
  )
}
