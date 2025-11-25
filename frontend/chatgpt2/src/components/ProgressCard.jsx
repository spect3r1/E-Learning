
export default function ProgressCard({ title, percent=0, extra }){
  return (
    <div className="card">
      <div className="row" style={{justifyContent:'space-between', alignItems:'center'}}>
        <h3>{title}</h3><b>{Math.round(percent)}%</b>
      </div>
      <div className="progress"><div style={{width: `${percent}%`}}></div></div>
      {extra && <div style={{marginTop:8}}><small className="muted">{extra}</small></div>}
    </div>
  )
}
