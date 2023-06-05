import React from "react";
import {ToDoEntry} from "@/models/ToDoEntry";

export const ApplicationContext = React.createContext<ApplicationContextData>(
    {
        message: null,
        setMessage: value => {},
        error: null,
        setError: value => {},
        entries: [],
        setEntries: value => {}
    }
)

export interface ApplicationContextData {
    message: string | null,
    setMessage: (value: string) => any,
    error: string | null,
    setError: (value: string) => any,
    entries: ToDoEntry[],
    setEntries: (value: ToDoEntry[]) => any
}
