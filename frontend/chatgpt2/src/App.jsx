
import { Routes, Route, Navigate } from 'react-router-dom'
import Navbar from './components/Navbar.jsx'
import ProtectedRoute from './components/ProtectedRoute.jsx'
import LoginPage from './pages/LoginPage.jsx'
import RegisterPage from './pages/RegisterPage.jsx'
import CoursesPage from './pages/CoursesPage.jsx'
import AssignedCoursesPage from './pages/AssignedCoursesPage.jsx'
import CourseDetailPage from './pages/CourseDetailPage.jsx'
import QuizPage from './pages/QuizPage.jsx'
import AssignmentPage from './pages/AssignmentPage.jsx'
import DashboardPage from './pages/DashboardPage.jsx'
import InstructorPage from './pages/InstructorPage.jsx'
import AdminPage from './pages/AdminPage.jsx'
import NotFound from './pages/NotFound.jsx'
export default function App(){
  return (<>
    <Navbar/>
    <div className="container">
      <Routes>
        <Route path="/" element={<Navigate to="/assigned" />} />
        <Route path="/login" element={<LoginPage/>} />
        <Route path="/register" element={<RegisterPage/>} />
        <Route path="/courses" element={<CoursesPage/>} />
        <Route path="/assigned" element={<ProtectedRoute><AssignedCoursesPage/></ProtectedRoute>} />
        <Route path="/courses/:id" element={<ProtectedRoute><CourseDetailPage/></ProtectedRoute>} />
        <Route path="/courses/:id/quizzes" element={<ProtectedRoute><QuizPage/></ProtectedRoute>} />
        <Route path="/courses/:id/assignments" element={<ProtectedRoute><AssignmentPage/></ProtectedRoute>} />
        <Route path="/dashboard" element={<ProtectedRoute><DashboardPage/></ProtectedRoute>} />
        <Route path="/instructor" element={<ProtectedRoute roles={['INSTRUCTOR','ADMIN']}><InstructorPage/></ProtectedRoute>} />
        <Route path="/admin" element={<ProtectedRoute roles={['ADMIN']}><AdminPage/></ProtectedRoute>} />
        <Route path="*" element={<NotFound/>} />
      </Routes>
    </div>
    <div className="footer">E-Learn v2 © {new Date().getFullYear()}</div>
  </>)
}
