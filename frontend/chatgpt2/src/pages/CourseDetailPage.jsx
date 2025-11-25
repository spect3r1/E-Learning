
import { useEffect, useState } from 'react'
import { useParams, Link } from 'react-router-dom'
import { CourseApi, ProgressApi } from '../services/api.js'
import Loading from '../components/Loading.jsx'
import ProgressCard from '../components/ProgressCard.jsx'
export default function CourseDetailPage(){
  const { id } = useParams()
  const [course,setCourse]=useState(null); const [progress,setProgress]=useState(null)
  const [loading,setLoading]=useState(true)
  useEffect(()=>{ (async()=>{
    try{ const c = await CourseApi.byId(id); setCourse(c); const p = await ProgressApi.course(id); setProgress(p) } finally{ setLoading(false) }
  })() },[id])
  if(loading) return <Loading/>
  if(!course) return <div className="card">Not found</div>
  return (<div className="grid">
    <div className="card">
      <h2>{course.title}</h2>
      <p><small className="muted">{course.description}</small></p>
      <div className="row">
        <span className="badge">#{course.category?.name || 'General'}</span>
        {course.instructor?.fullName && <span className="badge">by {course.instructor.fullName}</span>}
      </div>
      <div className="row" style={{marginTop:12}}>
        <Link className="button" to={`/courses/${id}/quizzes`}>Quizzes</Link>
        <Link className="button secondary" to={`/courses/${id}/assignments`}>Assignments</Link>
      </div>
    </div>
    <div className="grid">
      <ProgressCard title="Overall Progress" percent={(progress?.overall||0)*100} />
      <ProgressCard title="Lessons Completed" percent={(progress?.lessons||0)*100} extra={`${Math.round((progress?.lessons||0)*100)}% lessons done`} />
      <ProgressCard title="Quiz Average" percent={(progress?.quizAvg||0)*100} extra={`Avg score ${Math.round((progress?.quizAvg||0)*100)}%`} />
      <ProgressCard title="Assignments" percent={(progress?.assignments||0)*100} extra={`On-time submissions ${progress?.onTime || 0}`} />
    </div>
  </div>)
}
