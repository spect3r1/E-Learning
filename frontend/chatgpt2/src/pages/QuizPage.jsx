
import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { QuizApi } from '../services/api.js'
import Loading from '../components/Loading.jsx'
export default function QuizPage(){
  const { id } = useParams()
  const [quizzes,setQuizzes]=useState([]); const [active,setActive]=useState(null)
  const [answers,setAnswers]=useState({}); const [result,setResult]=useState(null)
  const [loading,setLoading]=useState(true)
  useEffect(()=>{ (async()=>{ try{ setQuizzes(await QuizApi.list(id)) } finally{ setLoading(false) } })() },[id])
  const start = (q)=>{ setActive(q); setAnswers({}); setResult(null) }
  const setAns = (qid, val)=> setAnswers(a => ({...a, [qid]: val}))
  const submit = async ()=>{ const r = await QuizApi.submit(active.id, answers); setResult(r) }
  if(loading) return <Loading/>
  if(!active) return (<div><h2>Quizzes</h2>
    <div className="grid cols-3">{quizzes.map(q => (<div key={q.id} className="card">
      <h3>{q.title}</h3><small className="muted">{q.description}</small>
      <div style={{marginTop:12}}><button className="button" onClick={()=>start(q)}>Start</button></div>
    </div>))}</div></div>)
  return (<div className="card">
    <h2>{active.title}</h2>
    {(active.questions || []).map(qq => (<div key={qq.id} style={{margin:'12px 0'}}>
      <b>{qq.prompt}</b><br/>
      {qq.type === 'MCQ' && (JSON.parse(qq.optionsJson || '[]')).map((opt, idx)=>(
        <label key={idx} style={{display:'block',marginTop:6}}>
          <input type="radio" name={`q_${qq.id}`} onChange={()=>setAns(qq.id,opt)} /> {opt}
        </label>
      ))}
      {qq.type === 'TRUE_FALSE' && (<div className="row" style={{marginTop:6}}>
        <button className="button" onClick={()=>setAns(qq.id,true)}>True</button>
        <button className="button secondary" onClick={()=>setAns(qq.id,false)}>False</button>
      </div>)}
      {qq.type === 'SHORT' && (<input className="input" placeholder="Your answer..." onChange={(e)=>setAns(qq.id, e.target.value)} />)}
    </div>))}
    <div className="row" style={{marginTop:12}}><button className="button" onClick={submit}>Submit</button></div>
    {result && <div style={{marginTop:12}}><b>Score:</b> {Math.round((result.score||0)*100)}%</div>}
  </div>)
}
