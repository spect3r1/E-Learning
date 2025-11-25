import { useForm } from 'react-hook-form'
import useAuthStore from '@stores/auth'
import { useNavigate, Link } from 'react-router-dom'

type Form = { name: string; email: string; password: string }

export default function Register() {
  const { register, handleSubmit } = useForm<Form>()
  const signup = useAuthStore((s) => s.register)
  const navigate = useNavigate()

  const onSubmit = async (data: Form) => {
    try {
      await signup(data.name, data.email, data.password)
      navigate('/courses')
    } catch {
      alert('Registration failed')
    }
  }

  return (
    <div className="max-w-md mx-auto">
      <h2 className="text-2xl font-semibold mb-4">Create account</h2>
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">
        <div>
          <label className="label">Name</label>
          <input className="input" {...register('name', { required: true })} />
        </div>
        <div>
          <label className="label">Email</label>
          <input type="email" className="input" {...register('email', { required: true })} />
        </div>
        <div>
          <label className="label">Password</label>
          <input type="password" className="input" {...register('password', { required: true, minLength: 6 })} />
        </div>
        <button type="submit" className="btn btn-primary w-full">Sign up</button>
      </form>
      <p className="text-sm text-gray-600 mt-3">Already have an account? <Link to="/login" className="text-blue-600">Login</Link></p>
    </div>
  )
}