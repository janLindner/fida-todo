import {Box, Card, Grid, IconButton, Tooltip, Typography} from "@mui/material";
import React, {useContext, useEffect, useState} from "react";
import {ApplicationContext} from "@/app/ApplicationContext";
import {ToDoEntry} from "@/models/ToDoEntry";
import {Check, DeleteForeverOutlined} from "@mui/icons-material";
import api from "@/app/api";
import moment from "moment";

const ActiveToDos = () => {
    const {entries, setEntries, setMessage, setError} = useContext(ApplicationContext)
    const [activeEntries, setActiveEntries] = useState<ToDoEntry[]>([])

    useEffect(() => {
        setActiveEntries(entries.filter(entry => !entry.completed))
    }, [entries])

    const deleteEntry = (id: string) => {
        api.deleteEntry(id)
            .then(() => {
                setEntries(entries.filter(entry => entry.id !== id));
                setMessage('Aufgabe erfolgreich gelöscht.')
            })
            .catch(() => setError('Beim Löschen der Aufgabe ist ein Fehler aufgetreten!'))
    }

    const completeEntry = (entry: ToDoEntry) => {
        api.updateEntry(entry.id, {...entry, completed : true})
            .then(() => {
                setEntries([...entries.filter(e => e.id !== entry.id), {...entry, completed: true}]);
                setMessage('Aufgabe als erledigt markiert.')
            })
            .catch(() => setError('Beim Bearbeiten der Aufgabe ist ein Fehler aufgetreten!'))
    }

    return <Card sx={{width: '100%'}}>
        <Typography variant='h5' p={2}>Aktive Aufgaben</Typography>

        <Box px={2} pb={2}>
            {activeEntries.map((entry: ToDoEntry, index: number) => <Grid container key={index}>
                <Grid item xs={1}>
                    <Tooltip title='Aufgabe als erledigt markieren.'>
                        <IconButton data-testid='complete-button' onClick={() => completeEntry(entry)}>
                            <Check/>
                        </IconButton>
                    </Tooltip>
                </Grid>
                <Grid item xs={9} sx={{display: 'flex', placeItems: 'center'}}>
                    <Typography>
                        {entry.content}
                    </Typography>
                </Grid>
                <Grid item xs={1} sx={{display: 'flex', placeItems: 'center'}}>
                    <Typography color={moment(entry.dueDate).isBefore(new Date()) ? 'error' : 'text'}>
                        {moment(entry.dueDate).format("DD.MM.yyyy")}
                    </Typography>
                </Grid>
                <Grid item xs={1}>
                    <Tooltip title='Aufgabe dauerhaft löschen!'>
                        <IconButton data-testid='delete-button' onClick={() => deleteEntry(entry.id)}>
                            <DeleteForeverOutlined/>
                        </IconButton>
                    </Tooltip>
                </Grid>
            </Grid>)}
        </Box>
    </Card>
}

export default ActiveToDos
