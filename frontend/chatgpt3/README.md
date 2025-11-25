# E‑Learning Frontend (React + TypeScript + Tailwind)

A modern, fully‑styled frontend for your Spring Boot e‑learning backend.

## Features
- React + TypeScript + Vite
- Tailwind CSS styling (modern UI, responsive)
- React Router v6 routes (public + protected + role‑based)
- Auth (demo fallback) + Zustand state
- Courses (browse, details) with graceful mock fallback when API is offline
- Lesson player (text, video, quiz)
- Instructor & Admin dashboards

## Quick Start
```bash
npm install
cp .env.example .env         # point VITE_API_BASE_URL to your backend
npm run dev                  # http://localhost:5173
```

> If your backend is not ready yet, the UI still works with **demo fallbacks** for Auth and Courses.

## Building
```bash
npm run build
npm run preview
```

## Tech
- React 18, TypeScript 5
- Vite 5, @vitejs/plugin-react
- TailwindCSS 3, PostCSS, Autoprefixer
- Axios, Zustand, React Hook Form, React Router v6