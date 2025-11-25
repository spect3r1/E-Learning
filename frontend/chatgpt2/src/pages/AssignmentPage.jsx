
import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { AssignmentApi } from '../services/api.js'
import Loading from '../components/Loading.jsx'
export default function AssignmentPage(){
  const { id } = useParams()
  const [items,setItems]=useState([]); const [loading,setLoading]=useState(true)
  const [status,setStatus]=useState('')
  useEffect(()=>{ (async()=>{ try{ setItems(await AssignmentApi.list(id)) } finally{ setLoading(false) } })() },[id])
  const submit = async (aid)=>{
    setStatus('Submitting...')
    try{ await AssignmentApi.submit(aid, { text:`My submission for assignment ${aid}` }); setStatus('Submitted ✔') } catch{ setStatus('Submit failed') }
  }
  if(loading) return <Loading/>
  return (<div>
    <h2>Assignments</h2>
    <div className="grid cols-3">
      {items.map(a => (<div key={a.id} className="card">
        <h3>{a.title}</h3><small className="muted">{a.description}</small><br/>
        <small className="muted">Due: {a.dueDate}</small>
        <div style={{marginTop:12}}><button className="button" onClick={()=>submit(a.id)}>Submit</button></div>
      </div>))}
    </div>
    {status && <div style={{marginTop:12}}><small className="muted">{status}</small></div>}
  </div>)
}
