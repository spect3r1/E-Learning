import { Routes, Route, useParams, Link } from 'react-router-dom'
import LessonView from './lesson/LessonView'

export default function Learn() {
  const { courseId } = useParams()
  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h2 className="text-2xl font-semibold">Learning</h2>
        <Link to={`/courses/${courseId}`} className="btn btn-secondary">Back to course</Link>
      </div>
      <Routes>
        <Route path="lesson/:lessonId" element={<LessonView />} />
      </Routes>
    </div>
  )
}