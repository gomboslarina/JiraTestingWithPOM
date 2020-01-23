This is a manual test for People page in Glass documentation.
Test scenario: check if glass People page correlates with Jira "Users and roles" page

Jira page url: https://jira2.codecool.codecanvas.hu/plugins/servlet/project-config/TIA/roles
Glass page url: https://jira2.codecool.codecanvas.hu/projects/TIA?selectedItem=com.codecanvas.glass:glass - click on People link

Check the following on the Jira page:
- Project lead
- Default Assignee
- Administrators table
- Developers table

Check the related data on the Glass page:
- data must be the same as on project Jira page

-- TEST RESULTS --

DEFAULTS

|            | Project lead | Default Assignee |
|------------|--------------|------------------|
| Jira page  |  TIA Admin   |   Unassigned     |
|            |              |                  |
| Glass page |  TIA Admin   |No data about this|


USERS BY ROLE - ADMINISTRATORS

|            | Name                   | Username | Email address      |
|------------|------------------------|----------|--------------------|
| Jira page  | jira-administrators    |          |                    | // GLASS SHOWS TWO admin instead of one. Is it a bug?
| Glass page | (2)jira-administrators |          |                    |
| Jira page  | TIA Admin              | tiaadmin | tiaadmin@gmail.com |
| Glass page | TIA Admin              | tiaadmin | tiaadmin@gmail.com |


USERS BY ROLE - DEVELOPERS

|            | Name     | Username | Email address     | Last session              |
|------------|----------|----------|-------------------|---------------------------|
| Jira page  | TIA User | tiauser  | tiauser@gmail.com | No data but column exists |
| Glass page | TIA User | tiauser  | tiauser@gmail.com | No such column            | // NO COLUMN at glass - is it because there is no data on Jira page?





