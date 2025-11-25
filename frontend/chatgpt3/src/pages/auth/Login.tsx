import { useForm } from 'react-hook-form'
import useAuthStore from '@stores/auth'
import { useLocation, useNavigate, Link } from 'react-router-dom'

type Form = { email: string; password: string }

export default function Login() {
  const { register, handleSubmit } = useForm<Form>()
  const login = useAuthStore((s) => s.login)
  const navigate = useNavigate()
  const location = useLocation() as any
  const from = location.state?.from?.pathname || '/courses'

  const onSubmit = async (data: Form) => {
    try {
      await login(data.email, data.password)
      navigate(from, { replace: true })
    } catch {
      alert('Invalid credentials')
    }
  }

  return (
    <div className="max-w-md mx-auto">
      <h2 className="text-2xl font-semibold mb-4">Sign in</h2>
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">
        <div>
          <label className="label">Email</label>
          <input type="email" className="input" {...register('email', { required: true })} />
        </div>
        <div>
          <label className="label">Password</label>
          <input type="password" className="input" {...register('password', { required: true })} />
        </div>
        <button className="btn btn-primary w-full" type="submit">Login</button>
      </form>
      <p className="text-sm text-gray-600 mt-3">No account? <Link to="/register" className="text-blue-600">Create one</Link></p>
    </div>
  )
}