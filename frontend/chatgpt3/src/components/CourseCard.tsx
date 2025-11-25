import { Link } from 'react-router-dom'
import { Course } from '@types/models'

export default function CourseCard({ course }: { course: Course }) {
  return (
    <div className="card">
      <div className="flex items-start justify-between">
        <h3 className="text-lg font-semibold">{course.title}</h3>
        {course.category && <span className="badge">{course.category}</span>}
      </div>
      <p className="text-gray-600 mt-2 line-clamp-3">{course.description}</p>
      <div className="mt-4 flex items-center justify-between">
        <Link to={`/courses/${course.id}`} className="btn btn-primary">View</Link>
        <span className="text-sm text-gray-500">{course.modules?.length ?? 0} modules</span>
      </div>
    </div>
  )
}