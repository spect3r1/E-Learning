import { useEffect, useState } from 'react'
import api from '@services/api'

interface AdminUser { id: string; name: string; email: string; role: string }

export default function AdminDashboard() {
  const [users, setUsers] = useState<AdminUser[]>([])
  const [categories, setCategories] = useState<string[]>([])
  const [newCat, setNewCat] = useState('')

  useEffect(() => {
    api.get('/admin/users').then((r) => setUsers(r.data)).catch(() => setUsers([]))
    api.get('/admin/categories').then((r) => setCategories(r.data)).catch(() => setCategories([]))
  }, [])

  const updateRole = async (id: string, role: string) => {
    await api.put(`/admin/users/${id}/role`, { role })
    setUsers((u) => u.map((x) => (x.id === id ? { ...x, role } : x)))
  }

  const addCategory = async () => {
    if (!newCat) return
    const res = await api.post('/admin/categories', { name: newCat })
    setCategories((c) => [...c, res.data.name || newCat])
    setNewCat('')
  }

  return (
    <div className="space-y-6">
      <h2 className="text-2xl font-semibold">Admin Dashboard</h2>

      <div className="card overflow-x-auto">
        <h3 className="text-lg font-semibold mb-3">Users</h3>
        <table className="min-w-full text-sm">
          <thead><tr className="text-left text-gray-500"><th className="py-2">Name</th><th>Email</th><th>Role</th><th>Action</th></tr></thead>
          <tbody>
            {users.map((u) => (
              <tr key={u.id} className="border-t">
                <td className="py-2">{u.name}</td>
                <td>{u.email}</td>
                <td>{u.role}</td>
                <td className="space-x-2">
                  <button onClick={() => updateRole(u.id, 'STUDENT')} className="btn btn-secondary">Student</button>
                  <button onClick={() => updateRole(u.id, 'INSTRUCTOR')} className="btn btn-secondary">Instructor</button>
                  <button onClick={() => updateRole(u.id, 'ADMIN')} className="btn btn-secondary">Admin</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="card">
        <h3 className="text-lg font-semibold mb-3">Course Categories</h3>
        <div className="flex items-center gap-2">
          <input className="input" value={newCat} onChange={(e) => setNewCat(e.target.value)} placeholder="New category" />
          <button onClick={addCategory} className="btn btn-primary">Add</button>
        </div>
        <ul className="mt-3 list-disc pl-6 text-gray-700">
          {categories.map((c) => <li key={c}>{c}</li>)}
        </ul>
      </div>
    </div>
  )
}