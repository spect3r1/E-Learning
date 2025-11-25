import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import api from '@services/api'
import { Lesson } from '@types/models'
import QuizView from './QuizView'

export default function LessonView() {
  const { courseId, lessonId } = useParams()
  const [lesson, setLesson] = useState<Lesson | null>(null)

  useEffect(() => {
    if (!courseId || !lessonId) return
    api.get(`/courses/${courseId}/lessons/${lessonId}`)
       .then((res) => setLesson(res.data))
       .catch(() => setLesson(null))
  }, [courseId, lessonId])

  if (!lesson) return <p>Loading...</p>

  return (
    <div className="card space-y-4">
      <h3 className="text-xl font-semibold">{lesson.title}</h3>
      {lesson.type === 'VIDEO' && lesson.videoUrl && (
        <video controls className="w-full rounded-xl">
          <source src={lesson.videoUrl} />
        </video>
      )}
      {lesson.type === 'TEXT' && (
        <article className="prose max-w-none" dangerouslySetInnerHTML={{ __html: lesson.content || '' }} />
      )}
      {lesson.type === 'QUIZ' && lesson.quiz && (
        <QuizView quiz={lesson.quiz} courseId={courseId!} lessonId={lessonId!} />
      )}
    </div>
  )
}