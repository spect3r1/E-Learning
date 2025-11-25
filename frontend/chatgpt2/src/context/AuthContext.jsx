
import React, { createContext, useContext, useMemo, useState } from 'react'
const AuthCtx = createContext(null)
export function AuthProvider({ children }){
  const [token, setToken] = useState(localStorage.getItem('accessToken') || null)
  const [roles, setRoles] = useState(JSON.parse(localStorage.getItem('roles') || '[]'))
  const [profile, setProfile] = useState(JSON.parse(localStorage.getItem('profile') || 'null'))
  const login = async (usernameOrEmail, password) => {
    const res = await fetch(import.meta.env.VITE_API_BASE_URL + '/api/auth/login', { method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({ usernameOrEmail, password }) })
    if(!res.ok) throw new Error('Invalid credentials')
    const data = await res.json()
    localStorage.setItem('accessToken', data.accessToken)
    if(data.roles) localStorage.setItem('roles', JSON.stringify(data.roles))
    if(data.profile) localStorage.setItem('profile', JSON.stringify(data.profile))
    setToken(data.accessToken); setRoles(data.roles || []); setProfile(data.profile || null); return true
  }
  const register = async (payload) => {
    const res = await fetch(import.meta.env.VITE_API_BASE_URL + '/api/auth/register', { method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload) })
    if(!res.ok) throw new Error('Registration failed'); return res.json()
  }
  const logout = () => { localStorage.removeItem('accessToken'); localStorage.removeItem('roles'); localStorage.removeItem('profile'); setToken(null); setRoles([]); setProfile(null) }
  const value = useMemo(()=>({ token, roles, profile, login, register, logout }), [token, roles, profile])
  return <AuthCtx.Provider value={value}>{children}</AuthCtx.Provider>
}
export const useAuth = () => useContext(AuthCtx)
