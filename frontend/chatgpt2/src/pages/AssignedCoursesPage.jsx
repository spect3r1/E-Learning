
import { useEffect, useState } from 'react'
import { CourseApi } from '../services/api.js'
import CourseCard from '../components/CourseCard.jsx'
import Loading from '../components/Loading.jsx'
export default function AssignedCoursesPage(){
  const [data,setData]=useState(null); const [loading,setLoading]=useState(true)
  useEffect(()=>{ (async()=>{ try{ setData(await CourseApi.assigned()) } finally{ setLoading(false) } })() },[])
  return (<div>
    <h1>My Courses</h1>
    <small className="muted">Only courses assigned by your college are available. Others appear locked in the catalog.</small>
    {loading && <Loading/>}
    {!loading && data && (<div className="grid cols-3">{(data || []).map(c => <CourseCard key={c.id} course={{...c, assigned:true}} />)}</div>)}
  </div>)
}
