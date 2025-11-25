
import { useEffect, useState } from 'react'
import { AdminApi } from '../services/api.js'
import Loading from '../components/Loading.jsx'
export default function AdminPage(){
  const [users,setUsers]=useState([]); const [cats,setCats]=useState([]); const [loading,setLoading]=useState(true)
  useEffect(()=>{ (async()=>{ try{ const [u,c] = await Promise.all([AdminApi.users(), AdminApi.categories()]); setUsers(u); setCats(c) } finally{ setLoading(false) } })() },[])
  if(loading) return <Loading/>
  return (<div className="grid">
    <div className="card">
      <h2>Users</h2>
      <table className="table">
        <thead><tr><th>Username</th><th>Email</th><th>Roles</th></tr></thead>
        <tbody>{users.map(u => (<tr key={u.id}><td>{u.username}</td><td><small className="muted">{u.email}</small></td><td>{(u.roles||[]).join(', ')}</td></tr>))}</tbody>
      </table>
    </div>
    <div className="card">
      <h2>Categories</h2>
      <ul>{cats.map(c => <li key={c.id}><b>{c.name}</b> — <small className="muted">{c.description}</small></li>)}</ul>
    </div>
  </div>)
}
