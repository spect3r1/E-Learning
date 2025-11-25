import { Routes, Route, Navigate } from 'react-router-dom'
import PublicLayout from '@layouts/PublicLayout'
import DashboardLayout from '@layouts/DashboardLayout'
import Login from '@pages/auth/Login'
import Register from '@pages/auth/Register'
import Courses from '@pages/courses/Courses'
import CourseDetails from '@pages/courses/CourseDetails'
import Learn from '@pages/learn/Learn'
import Profile from '@pages/profile/Profile'
import InstructorDashboard from '@pages/instructor/InstructorDashboard'
import AdminDashboard from '@pages/admin/AdminDashboard'
import { RequireAuth, RequireRole } from '@components/RouteGuards'

export default function App() {
  return (
    <Routes>
      <Route element={<PublicLayout />}>
        <Route path="/" element={<Navigate to="/courses" replace />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/courses" element={<Courses />} />
        <Route path="/courses/:courseId" element={<CourseDetails />} />
      </Route>

      <Route element={<DashboardLayout />}>
        <Route element={<RequireAuth />}>
          <Route path="/learn/:courseId/*" element={<Learn />} />
          <Route path="/profile" element={<Profile />} />
          <Route element={<RequireRole roles={['INSTRUCTOR']} />}>
            <Route path="/instructor" element={<InstructorDashboard />} />
          </Route>
          <Route element={<RequireRole roles={['ADMIN']} />}>
            <Route path="/admin" element={<AdminDashboard />} />
          </Route>
        </Route>
      </Route>

      <Route path="*" element={<Navigate to="/" />} />
    </Routes>
  )
}