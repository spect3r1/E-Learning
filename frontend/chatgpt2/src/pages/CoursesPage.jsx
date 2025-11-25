
import { useEffect, useState } from 'react'
import { CourseApi } from '../services/api.js'
import CourseCard from '../components/CourseCard.jsx'
import Loading from '../components/Loading.jsx'
export default function CoursesPage(){
  const [q,setQ]=useState(''); const [page,setPage]=useState(0)
  const [data,setData]=useState(null); const [loading,setLoading]=useState(true)
  const load = async()=>{ setLoading(true); try{ setData(await CourseApi.catalog(q,page,12)) } finally{ setLoading(false) } }
  useEffect(()=>{ load() },[page])
  return (<div>
    <h1>Course Catalog</h1>
    <div className="row" style={{marginBottom:12}}>
      <input className="input" placeholder="Search courses..." value={q} onChange={e=>setQ(e.target.value)} />
      <button className="button" onClick={()=>{ setPage(0); load() }}>Search</button>
    </div>
    {loading && <Loading/>}
    {!loading && data && (<>
      <div className="grid cols-3">
        {(data.content || data).map(c => <CourseCard key={c.id} course={c} />)}
      </div>
      <div className="row" style={{marginTop:16}}>
        <button className="button secondary" disabled={page<=0} onClick={()=>setPage(p=>p-1)}>Prev</button>
        <button className="button" onClick={()=>setPage(p=>p+1)}>Next</button>
      </div>
    </>)}
  </div>)
}
