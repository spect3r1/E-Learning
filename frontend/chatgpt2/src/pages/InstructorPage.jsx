
import { useEffect, useState } from 'react'
import { AdminApi, CourseApi } from '../services/api.js'
export default function InstructorPage(){
  const [catalog,setCatalog]=useState([]); const [students,setStudents]=useState([])
  const [selCourse,setSelCourse]=useState(''); const [selStudent,setSelStudent]=useState(''); const [msg,setMsg]=useState('')
  useEffect(()=>{ (async()=>{
    const list = await CourseApi.catalog('',0,100); setCatalog(list.content || list)
    const u = await AdminApi.users(); setStudents(u.filter(x => (x.roles||[]).includes('STUDENT')))
  })() }, [])
  const assign = async()=>{ try{ await AdminApi.assignCourse(Number(selStudent), Number(selCourse)); setMsg('Assigned ✔') } catch{ setMsg('Failed to assign') } }
  return (<div style={{maxWidth:700}}>
    <h1>Instructor Panel</h1>
    <div className="card">
      <h3>Assign Course to Student</h3>
      <label>Course</label>
      <select className="input" value={selCourse} onChange={e=>setSelCourse(e.target.value)}><option value="">Select course</option>{catalog.map(c => <option key={c.id} value={c.id}>{c.title}</option>)}</select>
      <label>Student</label>
      <select className="input" value={selStudent} onChange={e=>setSelStudent(e.target.value)}><option value="">Select student</option>{students.map(s => <option key={s.id} value={s.id}>{s.username}</option>)}</select>
      <div style={{marginTop:12}}><button className="button" onClick={assign} disabled={!selCourse || !selStudent}>Assign</button></div>
      {msg && <div style={{marginTop:12}}><small className="muted">{msg}</small></div>}
    </div>
  </div>)
}
