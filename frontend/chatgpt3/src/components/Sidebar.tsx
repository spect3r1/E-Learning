import { NavLink } from 'react-router-dom'
import useAuthStore from '@stores/auth'

export default function Sidebar() {
  const { user } = useAuthStore()

  return (
    <nav className="h-full p-4 space-y-2 bg-white dark:bg-gray-800 dark:text-gray-100">
      <NavLink
        to="/profile"
        className="block px-3 py-2 rounded hover:bg-gray-100 dark:hover:bg-gray-700"
      >
        Profile
      </NavLink>

      <NavLink
        to="/courses"
        className="block px-3 py-2 rounded hover:bg-gray-100 dark:hover:bg-gray-700"
      >
        Courses
      </NavLink>

      {user?.role === 'INSTRUCTOR' && (
        <NavLink
          to="/instructor"
          className="block px-3 py-2 rounded hover:bg-gray-100 dark:hover:bg-gray-700"
        >
          Instructor
        </NavLink>
      )}

      {user?.role === 'ADMIN' && (
        <NavLink
          to="/admin"
          className="block px-3 py-2 rounded hover:bg-gray-100 dark:hover:bg-gray-700"
        >
          Admin
        </NavLink>
      )}
    </nav>
  )
}

