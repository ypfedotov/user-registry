import React, {useCallback, useEffect, useState} from "react";
import "./SearchForm.scss";
import {User} from "../model/User";
import backend from "../backend/backend";

interface Props {
    resultCallback: (users: User[]) => void,
}

function SearchForm(props: Props) {
    const {resultCallback} = props;
    const [query, setQuery] = useState<string>("");

    const runSearch = useCallback(async () => {
        const matchingUsers = query
            ? await backend.fetchUsersByName(query)
            : await backend.fetchUserList();

        resultCallback(matchingUsers);
    }, [query, resultCallback]);

    useEffect(() => {
        const timeoutId = setTimeout(runSearch, 1000);
        return () => clearTimeout(timeoutId);
    }, [runSearch]);


    return (
        <form id="search-form"
              onSubmit={e => {
                  e.preventDefault();
                  runSearch();
              }}
        >
            <i className="material-icons">search</i>
            <input type="text"
                   placeholder="Search by name"
                   value={query}
                   onChange={e => setQuery(e.target.value)}
                   />
        </form>
    );
}


export default SearchForm;
