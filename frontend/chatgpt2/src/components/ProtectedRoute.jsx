
import { Navigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext.jsx'
export default function ProtectedRoute({ children, roles }){
  const { token, roles: myRoles } = useAuth()
  if(!token) return <Navigate to="/login" replace />
  if(roles && roles.length && !myRoles?.some(r => roles.includes(r))) return <Navigate to="/assigned" replace />
  return children
}
