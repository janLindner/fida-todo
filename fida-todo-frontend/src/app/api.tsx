import {CreateRequest} from "@/models/CreateRequest";
import {ToDoEntry} from "@/models/ToDoEntry";
import {UpdateRequest} from "@/models/UpdateRequest";

const createEntry = (request: CreateRequest): Promise<ToDoEntry> => {
    return fetch("http://localhost:8080/fida-todo-1.0-SNAPSHOT/api/todos", {method: 'POST', body: JSON.stringify(request)})
        .then(response => response.json())
        .then(data => data as ToDoEntry)
}

const updateEntry = (id: string, request: UpdateRequest): Promise<any> => {
    return fetch(
        `http://localhost:8080/fida-todo-1.0-SNAPSHOT/api/todos/${id}`,
        {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(request)}
    )
}

const deleteEntry = (id: string): Promise<any> => {
    return fetch(`http://localhost:8080/fida-todo-1.0-SNAPSHOT/api/todos/${id}`, {method: 'DELETE'})
}

const getEntries = (): Promise<ToDoEntry[]> => {
    return fetch("http://localhost:8080/fida-todo-1.0-SNAPSHOT/api/todos")
        .then(response => response.json())
        .then(data => data as ToDoEntry[])
}

export default {
    createEntry,
    updateEntry,
    deleteEntry,
    getEntries
}
