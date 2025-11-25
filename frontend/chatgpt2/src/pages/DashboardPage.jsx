
import { useEffect, useState } from 'react'
import { ProgressApi } from '../services/api.js'
import Loading from '../components/Loading.jsx'
import ProgressCard from '../components/ProgressCard.jsx'
export default function DashboardPage(){
  const [items,setItems]=useState([]); const [loading,setLoading]=useState(true)
  useEffect(()=>{ (async()=>{ try{ setItems(await ProgressApi.myEnrollments()) } finally{ setLoading(false) } })() },[])
  if(loading) return <Loading/>
  return (<div>
    <h1>Student Dashboard</h1>
    <div className="grid cols-3">
      {items.map(e => (<ProgressCard key={e.id} title={e.course?.title || 'Course'} percent={(e.progress||0)*100} extra={`Enrolled at ${e.enrolledAt || ''}`} />))}
    </div>
  </div>)
}
