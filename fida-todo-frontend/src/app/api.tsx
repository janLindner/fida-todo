import {ToDoEntry} from "@/models/ToDoEntry";
import {UpdateRequest} from "@/models/UpdateRequest";

const API_URL = process.env.NEXT_PUBLIC_API_URL

const updateEntry = (id: string, request: UpdateRequest): Promise<any> => {
    return fetch(
        `${API_URL}/todos/${id}`,
        {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(request)}
    )
}

const deleteEntry = (id: string): Promise<any> => {
    return fetch(`${API_URL}/todos/${id}`, {method: 'DELETE'})
}

const getEntries = (): Promise<ToDoEntry[]> => {
    return fetch(`${API_URL}/todos`)
        .then(response => response.json())
        .then(data => data as ToDoEntry[])
}

export default {
    updateEntry,
    deleteEntry,
    getEntries
}
