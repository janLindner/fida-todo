import {fireEvent, render, screen, waitForElementToBeRemoved} from "@testing-library/react";
import ActiveToDos from "@/components/ActiveToDos/index";
import {ApplicationContext} from "@/app/ApplicationContext";
import api from "@/app/api";

describe('ActiveToDos', () => {

    beforeEach(() => {
        jest.clearAllMocks()
    })

    it('should render active todo', () => {
        // given
        const entries =[{id: '1', content: 'Content One', dueDate: '2022-01-01', completed: false}]

        // when
        render(
            <ApplicationContext.Provider value={{entries, setEntries: jest.fn, message: null, error: null, setError: jest.fn, setMessage: jest.fn}}>
                <ActiveToDos/>
            </ApplicationContext.Provider>
        )

        // then
        expect(screen.getByText('Content One')).toBeInTheDocument()
        expect(screen.getByText('01.01.2022')).toBeInTheDocument()
    })

    it('should delete todo', () => {
        // given
        const entries =[{id: '1', content: 'Content One', dueDate: '2022-01-01', completed: false}]
        const deleteMock = jest.fn(id => Promise.resolve())
        jest.spyOn(api, 'deleteEntry').mockImplementation(deleteMock)

        // when
        render(
            <ApplicationContext.Provider value={{entries, setEntries: jest.fn, message: null, error: null, setError: jest.fn, setMessage: jest.fn}}>
                <ActiveToDos/>
            </ApplicationContext.Provider>
        )
        const deleteButton = screen.getByTestId('delete-button')
        fireEvent(deleteButton, new MouseEvent('click', {bubbles: true, cancelable: true}))

        // then
        expect(deleteMock).toBeCalledWith('1')
        waitForElementToBeRemoved(screen.getByText('Content One'))
    })

    it('should complete todo', () => {
        // given
        const entries =[{id: '1', content: 'Content One', dueDate: '2022-01-01', completed: false}]
        const updateMock = jest.fn(id => Promise.resolve())
        jest.spyOn(api, 'updateEntry').mockImplementation(updateMock)

        // when
        render(
            <ApplicationContext.Provider value={{entries, setEntries: jest.fn, message: null, error: null, setError: jest.fn, setMessage: jest.fn}}>
                <ActiveToDos/>
            </ApplicationContext.Provider>
        )
        const deleteButton = screen.getByTestId('complete-button')
        fireEvent(deleteButton, new MouseEvent('click', {bubbles: true, cancelable: true}))

        // then
        expect(updateMock).toBeCalledWith('1', {id: '1', content: 'Content One', dueDate: '2022-01-01', completed: true})
        waitForElementToBeRemoved(screen.getByText('Content One'))
    })
})
