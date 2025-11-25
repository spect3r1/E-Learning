import { useForm } from 'react-hook-form'
import { createCourse } from '@services/courses'

type Form = { title: string; description: string; category?: string }

export default function InstructorDashboard() {
  const { register, handleSubmit, reset } = useForm<Form>()
  const onSubmit = async (data: Form) => {
    try {
      await createCourse({
        title: data.title,
        description: data.description,
        category: data.category,
        modules: [],
        instructorId: 'me',
        id: 'tmp'
      })
      alert('Course created')
      reset()
    } catch {
      alert('Failed to create course')
    }
  }

  return (
    <div className="space-y-4">
      <h2 className="text-2xl font-semibold">Instructor Dashboard</h2>
      <div className="card">
        <h3 className="text-lg font-semibold mb-3">Create Course</h3>
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">
          <div>
            <label className="label">Title</label>
            <input className="input" {...register('title', { required: true })} />
          </div>
          <div>
            <label className="label">Description</label>
            <textarea className="input" rows={4} {...register('description', { required: true })} />
          </div>
          <div>
            <label className="label">Category</label>
            <input className="input" {...register('category')} />
          </div>
          <button type="submit" className="btn btn-primary">Create</button>
        </form>
      </div>
    </div>
  )
}