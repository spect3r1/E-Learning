import { useEffect, useState } from 'react'
import { listCourses } from '@services/courses'
import CourseCard from '@components/CourseCard'
import { Course } from '@types/models'

export default function Courses() {
  const [courses, setCourses] = useState<Course[]>([])
  const [q, setQ] = useState('')

  useEffect(() => {
    listCourses(q).then(setCourses).catch(() => setCourses([]))
  }, [q])

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h2 className="text-2xl font-semibold">Browse Courses</h2>
        <input className="input max-w-xs" placeholder="Search..." value={q} onChange={(e) => setQ(e.target.value)} />
      </div>
      <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
        {courses.map((c) => <CourseCard key={c.id} course={c} />)}
      </div>
    </div>
  )
}