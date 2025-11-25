import { Navigate, Outlet, useLocation } from 'react-router-dom'
import useAuthStore from '@stores/auth'

export function RequireAuth() {
  const { user } = useAuthStore()
  const location = useLocation()
  if (!user) return <Navigate to="/login" state={{ from: location }} replace />
  return <Outlet />
}

export function RequireRole({ roles }: { roles: string[] }) {
  const { user } = useAuthStore()
  if (!user) return <Navigate to="/login" replace />
  if (!roles.includes(user.role)) return <Navigate to="/" replace />
  return <Outlet />
}