
const BASE = import.meta.env.VITE_API_BASE_URL
const authHeaders = () => { const t = localStorage.getItem('accessToken'); const h={'Content-Type':'application/json'}; if(t) h.Authorization = `Bearer ${t}`; return h }
export const fetchJson = async (url, opts={}) => { const res = await fetch(BASE+url, { ...opts, headers:{...authHeaders(), ...(opts.headers||{})} }); if(!res.ok) throw new Error(String(res.status)); return res.json() }
export const CourseApi = {
  catalog: (q='', page=0, size=12) => fetchJson(`/api/courses?` + new URLSearchParams({ q, page, size })),
  assigned: () => fetchJson(`/api/courses/assigned`),
  byId: (id) => fetchJson(`/api/courses/${id}`)
}
export const ProgressApi = {
  course: (courseId) => fetchJson(`/api/progress/${courseId}`),
  myEnrollments: () => fetchJson(`/api/me/enrollments`)
}
export const QuizApi = {
  list: (courseId) => fetchJson(`/api/courses/${courseId}/quizzes`),
  submit: (quizId, answers) => fetchJson(`/api/quizzes/${quizId}/submit`, { method:'POST', body: JSON.stringify({ answers }) })
}
export const AssignmentApi = {
  list: (courseId) => fetchJson(`/api/courses/${courseId}/assignments`),
  submit: (assignmentId, payload) => fetchJson(`/api/assignments/${assignmentId}/submit`, { method:'POST', body: JSON.stringify(payload) })
}
export const AdminApi = {
  users: () => fetchJson(`/api/admin/users`),
  categories: () => fetchJson(`/api/admin/categories`),
  assignCourse: (studentId, courseId) => fetchJson(`/api/admin/assignCourse`, { method:'POST', body: JSON.stringify({ studentId, courseId }) })
}
