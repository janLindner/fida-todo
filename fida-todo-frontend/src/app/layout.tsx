import './globals.css'
import React from "react";


export const metadata = {
  title: 'FIDA ToDo',
  description: 'Simple single page web application to display todos.',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
        <body>{children}</body>
    </html>
  )
}
