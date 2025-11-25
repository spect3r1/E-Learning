export type Role = 'STUDENT' | 'INSTRUCTOR' | 'ADMIN'

export interface User {
  id: string
  name: string
  email: string
  role: Role
  avatarUrl?: string
}

export interface Course {
  id: string
  title: string
  description: string
  category?: string
  instructorId: string
  modules: Module[]
}

export interface Module {
  id: string
  title: string
  order: number
  lessons: Lesson[]
}

export type LessonType = 'VIDEO' | 'TEXT' | 'QUIZ'

export interface Lesson {
  id: string
  title: string
  type: LessonType
  content?: string
  videoUrl?: string
  quiz?: Quiz
}

export interface Quiz {
  id?: string
  title: string
  questions: Question[]
}

export type QuestionType = 'MCQ' | 'TRUE_FALSE' | 'SHORT_ANSWER'

export interface Question {
  id: string
  type: QuestionType
  text: string
  options?: string[]
  answer?: string | boolean
}

export interface Enrollment {
  id: string
  courseId: string
  userId: string
  progress: number
}