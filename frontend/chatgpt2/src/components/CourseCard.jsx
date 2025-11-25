
import { Link } from 'react-router-dom'
export default function CourseCard({ course }){
  const locked = course.assigned === false
  return (
    <div className={"card " + (locked ? "locked" : "")}>
      <h3>{course.title}</h3>
      <p><small className="muted">{course.description || 'No description'}</small></p>
      <div className="row">
        <span className="badge">#{course.category?.name || 'General'}</span>
        {course.instructor?.fullName && <span className="badge">by {course.instructor.fullName}</span>}
      </div>
      <hr />
      <Link className="button" to={locked ? "#" : `/courses/${course.id}`} onClick={(e)=>{ if(locked) e.preventDefault() }}>
        {locked ? 'Locked' : 'Open'}
      </Link>
    </div>
  )
}
