import { useEffect } from 'react'
import { Link, NavLink } from 'react-router-dom'
import useAuthStore from '@stores/auth'

export default function Navbar() {
  const { user, logout } = useAuthStore()

  // 👇 move the toggle function up here (inside component, before return)
  const toggleDark = () => {
    const html = document.documentElement
    const isDark = html.classList.toggle('dark')
    localStorage.setItem('theme', isDark ? 'dark' : 'light')

  }

  useEffect(() => {
    if(localStorage.getItem('theme') === 'dark'){
      document.documentElement.classList.add('dark')
    }
  })

  return (
    <header className="bg-white dark:bg-gray-900 border-b border-gray-100 dark:border-gray-700">
      <div className="container-app flex items-center h-16 gap-4">
        <Link to="/courses" className="text-lg font-semibold text-gray-900 dark:text-gray-100">
          🎓 E-Learn
        </Link>

        <nav className="ml-auto flex items-center gap-4">
          <NavLink to="/courses" className="nav-link">Courses</NavLink>
          {user && <NavLink to="/profile" className="nav-link">Profile</NavLink>}
          {user?.role === 'INSTRUCTOR' && <NavLink to="/instructor" className="nav-link">Instructor</NavLink>}
          {user?.role === 'ADMIN' && <NavLink to="/admin" className="nav-link">Admin</NavLink>}

          {/* 🌙 Dark mode toggle button */}
          <button onClick={toggleDark} className="btn btn-secondary">
            🌙
          </button>

          {!user ? (
            <>
              <Link to="/login" className="btn btn-secondary">Login</Link>
              <Link to="/register" className="btn btn-primary">Sign up</Link>
            </>
          ) : (
            <button onClick={logout} className="btn btn-secondary">Logout</button>
          )}
        </nav>
      </div>
    </header>
  )
}

