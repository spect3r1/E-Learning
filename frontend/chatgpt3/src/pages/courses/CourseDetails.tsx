import { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import { getCourse, enroll } from '@services/courses'
import { Course } from '@types/models'

export default function CourseDetails() {
  const { courseId } = useParams()
  const [course, setCourse] = useState<Course | null>(null)

  useEffect(() => {
    if (!courseId) return
    getCourse(courseId).then(setCourse).catch(() => setCourse(null))
  }, [courseId])

  if (!course) return <p>Loading...</p>

  return (
    <div className="space-y-4">
      <div className="card">
        <h2 className="text-2xl font-semibold">{course.title}</h2>
        <p className="text-gray-600 mt-2">{course.description}</p>
        <button onClick={() => enroll(course.id).then(() => alert('Enrolled!')).catch(() => alert('Failed'))} className="btn btn-primary mt-4">
          Enroll
        </button>
      </div>

      <div className="card">
        <h3 className="text-lg font-semibold mb-2">Modules</h3>
        <ol className="space-y-3 list-decimal pl-5">
          {course.modules.map((m) => (
            <li key={m.id}>
              <div className="font-medium">{m.title}</div>
              <ul className="mt-1 space-y-1">
                {m.lessons.map((lesson) => (
                  <li key={lesson.id} className="flex items-center justify-between">
                    <span>{lesson.title} <span className="text-xs text-gray-500">({lesson.type})</span></span>
                    <Link to={`/learn/${course.id}/lesson/${lesson.id}`} className="text-blue-600 hover:underline">Open</Link>
                  </li>
                ))}
              </ul>
            </li>
          ))}
        </ol>
      </div>
    </div>
  )
}