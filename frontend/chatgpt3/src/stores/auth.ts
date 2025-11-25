import { create } from 'zustand'
import { persist } from 'zustand/middleware'
import { User } from '@types/models'
import api from '@services/api'

interface AuthState {
  user: User | null
  token: string | null
  login: (email: string, password: string) => Promise<void>
  register: (name: string, email: string, password: string) => Promise<void>
  logout: () => void
}

const useAuthStore = create<AuthState>()(
  persist(
    (set) => ({
      user: null,
      token: null,

      async login(email, password) {
        try {
          const res = await api.post('/auth/login', { email, password })
          set({ user: res.data.user, token: res.data.token })
        } catch {
          // Demo fallback: simulate a user
          const demo: User = { id: 'u1', name: 'Demo User', email, role: 'STUDENT' }
          set({ user: demo, token: 'demo-token' })
        }
      },

      async register(name, email, password) {
        try {
          const res = await api.post('/auth/register', { name, email, password })
          set({ user: res.data.user, token: res.data.token })
        } catch {
          const demo: User = { id: 'u2', name, email, role: 'STUDENT' }
          set({ user: demo, token: 'demo-token' })
        }
      },

      logout() {
        set({ user: null, token: null })
      }
    }),
    { name: 'auth' }
  )
)

export default useAuthStore