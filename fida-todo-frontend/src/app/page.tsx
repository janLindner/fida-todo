'use client'

import styles from './page.module.css'
import {ApplicationContext} from "@/app/ApplicationContext";
import React, {useEffect, useState} from "react";
import {ToDoEntry} from "@/models/ToDoEntry";
import api from "@/app/api";
import ActiveToDos from "@/components/ActiveToDos";
import {Alert} from "@mui/material";
import {Snackbar} from "@mui/base";

const Page = () => {
    const [entries, setEntries] = useState<ToDoEntry[]>([])
    const [message, setMessage] = useState<string | null>(null)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        api.getEntries().then(setEntries)
    }, [])

    return (
        <main className={styles.main}>
            <ApplicationContext.Provider value={{message, setMessage, error, setError, entries, setEntries}}>
                <ActiveToDos></ActiveToDos>
            </ApplicationContext.Provider>
            <Snackbar open={!!message} autoHideDuration={2000} onClose={() => setMessage(null)}>
                <Alert onClose={() => setMessage(null)} severity="success" sx={{width: '100%'}}>
                    {message}
                </Alert>
            </Snackbar>
            <Snackbar open={!!error} autoHideDuration={2000} onClose={() => setError(null)}>
                <Alert onClose={() => setError(null)} severity="error" sx={{width: '100%'}}>
                    {error}
                </Alert>
            </Snackbar>
        </main>

    );
}

export default Page
