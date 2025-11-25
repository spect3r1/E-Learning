import { useState } from 'react'
import api from '@services/api'
import { Quiz } from '@types/models'

export default function QuizView({ quiz, courseId, lessonId }: { quiz: Quiz; courseId: string; lessonId: string }) {
  const [answers, setAnswers] = useState<Record<string, string | boolean>>({})
  const [result, setResult] = useState<any>(null)

  const submit = async () => {
    const res = await api.post(`/courses/${courseId}/lessons/${lessonId}/quiz/${quiz.id}/submit`, { answers })
    setResult(res.data)
  }

  return (
    <div className="space-y-3">
      <h4 className="text-lg font-semibold">{quiz.title}</h4>
      {quiz.questions.map((q) => (
        <div key={q.id} className="p-4 border border-gray-200 rounded-xl">
          <p className="font-medium">{q.text}</p>
          {q.type === 'MCQ' && q.options?.map((opt) => (
            <label key={opt} className="flex items-center gap-2 mt-2">
              <input type="radio" name={q.id} onChange={() => setAnswers((a) => ({ ...a, [q.id]: opt }))} />
              <span>{opt}</span>
            </label>
          ))}
          {q.type === 'TRUE_FALSE' && ['true','false'].map((opt) => (
            <label key={opt} className="flex items-center gap-2 mt-2">
              <input type="radio" name={q.id} onChange={() => setAnswers((a) => ({ ...a, [q.id]: opt === 'true' }))} />
              <span>{opt.toUpperCase()}</span>
            </label>
          ))}
          {q.type === 'SHORT_ANSWER' && (
            <input className="input mt-2" type="text" onChange={(e) => setAnswers((a) => ({ ...a, [q.id]: e.target.value }))} />
          )}
        </div>
      ))}
      <button onClick={submit} className="btn btn-primary">Submit</button>
      {result && <p className="mt-2">Score: <span className="font-semibold">{result.score}%</span></p>}
    </div>
  )
}