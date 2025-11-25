import api from './api'
import { Course } from '@types/models'

// If backend is not available, fall back to mock courses for demo
const mockCourses: Course[] = [
  {
    id: '101',
    title: 'Introduction to Programming',
    description: 'Learn the basics of programming with hands-on examples.',
    category: 'CS',
    instructorId: 'instructor-1',
    modules: [
      { id: 'm1', title: 'Getting Started', order: 1, lessons: [
        { id: 'l1', title: 'Welcome', type: 'TEXT', content: '<p>Welcome to the course!</p>' },
        { id: 'l2', title: 'Your First Program', type: 'VIDEO', videoUrl: 'https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4' }
      ]}
    ]
  },
  {
    id: '102',
    title: 'Data Structures',
    description: 'Arrays, stacks, queues, and trees explained clearly.',
    category: 'CS',
    instructorId: 'instructor-2',
    modules: []
  }
]

export async function listCourses(q?: string): Promise<Course[]> {
  try {
    const res = await api.get('/courses', { params: { q } })
    return res.data
  } catch {
    // demo fallback
    return (mockCourses.filter(c => !q || c.title.toLowerCase().includes(q.toLowerCase())))
  }
}

export async function getCourse(id: string): Promise<Course> {
  try {
    const res = await api.get(`/courses/${id}`)
    return res.data
  } catch {
    const c = mockCourses.find(x => x.id === id)
    if (!c) throw new Error('Course not found')
    return c
  }
}

export async function enroll(courseId: string) {
  try {
    const res = await api.post(`/courses/${courseId}/enroll`)
    return res.data
  } catch {
    return { ok: true }
  }
}

export async function createCourse(payload: Partial<Course>) {
  try {
    const res = await api.post('/instructor/courses', payload)
    return res.data
  } catch {
    return { ok: true, id: Math.random().toString(36).slice(2) }
  }
}