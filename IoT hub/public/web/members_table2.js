const btnClassAdd = "btn btn-primary btn-block";
const btnClassDel = "btn btn-danger btn-block";

/**
 * This is a stateless view showing the table header.
 */

function Header(props) {

	console.info("memberName ",props.memberName);
	console.info("groupNames ",props.groupNames);

	var tds = props.groupNames.map(groupName => {
		var onClick = () => props.oncreateGroup(groupName,props.memberName);
		return <td key={groupName}>
			<button className={btnClassAdd} onClick={onClick}>{groupName}</button>
			</td>;
	});

	return (
		<thead>
		<tr>
			<th rowSpan="2" width="10%">Members</th>
			<th colSpan={props.groupNames.length}>Groups</th>
			<th rowSpan="2" width="10%">Remove from All Groups</th>
		</tr>
		<tr>
			{tds}
		</tr>
	</thead>
	);
}

/**
 * This is a stateless view showing one row.
 */
function Row(props) {
	
	var members = props.members;
	
	var tds = members.get_group_names().map(groupName => {
		var onChange = () => props.onMemberChange(props.memberName, groupName);
		var checked = members.is_member_in_group(props.memberName, groupName);
		return (<td key={groupName}>
		<input type="checkbox" onChange={onChange} checked={checked}/>	
			</td>);
	});
	var onAddClick = () => props.onAddMemberToAllGroups(props.memberName);
	var onDeleteClick = () => props.onDeleteMemberToAllGroups(props.memberName);
	var states=members.get_members_state(props.memberName);
	return (
		<tr>
			<td><button className={btnClassAdd} onClick={onAddClick}>{props.memberName}</button></td>
			{tds}
			<td><button className={btnClassDel} onClick={onDeleteClick}>XA</button></td>
			<td>State:{states} </td>
		</tr>
	);
}

/**
 * This is a stateless view showing the row for delete groups.
 */
function DeleteGroupsRow(props) {
	var tds = props.groupNames.map(groupName => {
		var onClick = () => props.onDeleteGroup(groupName);
		return <td key={groupName}>
			<button className={btnClassDel} onClick={onClick}>X</button>
			</td>;
	});

	return (
		<tr>
			<td></td>
			{tds}
			<td></td>
		</tr>
	);
}
function ChangeStateRow(props) {
	var tds = props.groupNames.map(groupName => {
		var onClickOn = () => props.onChangeGroupToOn(groupName);
		var onClickOff = () => props.onChangeGroupToOff(groupName);
		var onClickToggle = () => props.onChangeGroupToggle(groupName);
		return <td key={groupName}>
			<button onClick={onClickOn}>on</button>
			<button onClick={onClickOff}>off</button>
			<button onClick={onClickToggle}>toggle</button>
			</td>;
	});

	return (
		<tr>
			<td></td>
			{tds}
			<td></td>
		</tr>
	);
}
/**
 *  This is a stateless view showing inputs for add/replace groups.
 */
function AddGroup(props) {
	var onChangeName = event => props.onInputNameChange(event.target.value);
	var onChangeMembers = event => props.onInputMembersChange(event.target.value);

	return (
		<div>
			<label>Group Name</label>
			<input type="text" onChange={onChangeName} value={props.inputName}/>
			<label>Members</label>
			<input type="text" onChange={onChangeMembers} value={props.inputMembers}
				size="60" placeholder="e.g. a,b,c"/>
			<button className="btn btn-primary" onClick={props.onAddGroup}>
				Add/Replace</button>
		</div>
	);
}
/**
 * This is a stateless view showing the table body.
 */
function Body(props) {
	var rows = props.members.get_member_names().map(memberName =>
		<Row key={memberName} memberName={memberName} members={props.members}
			onMemberChange={props.onMemberChange}
			onAddMemberToAllGroups={props.onAddMemberToAllGroups} 
			onDeleteMemberToAllGroups={props.onDeleteMemberToAllGroups}
			/>);

	return (
		<tbody>
			{rows}
			<ChangeStateRow groupNames={props.members.get_group_names()}
				onChangeGroupToOn={props.onChangeGroupToOn}
				onChangeGroupToOff={props.onChangeGroupToOff}
				onChangeGroupToggle={props.onChangeGroupToggle}/>
			<DeleteGroupsRow groupNames={props.members.get_group_names()}
				onDeleteGroup={props.onDeleteGroup} />
			<tr><td colSpan="7">
			<AddGroup inputName={props.inputName} inputMembers={props.inputMembers}
					onInputNameChange={props.onInputNameChange}
					onInputMembersChange={props.onInputMembersChange}
					onAddGroup={props.onAddGroup} />
			</td></tr>
		</tbody>
	);
}

/**
 * This is a stateless view showing the whole members table.
 */
function MembersTable(props) {
	console.info("MembersTable()");
	if (props.members.get_group_names().length == 0)
		return (<div>There are no groups.
		<AddGroup inputName={props.inputName} inputMembers={props.inputMembers}
					onInputNameChange={props.onInputNameChange}
					onInputMembersChange={props.onInputMembersChange}
					onAddGroup={props.onAddGroup} /></div>);

	return (
		<table className="table table-striped table-bordered">
			<Header groupNames={props.members.get_group_names()} memberName={props.members.get_member_names()} onDeleteGroup={props.onDeleteGroup} 
			oncreateGroup={props.oncreateGroup}  />
			<Body members={props.members}
				inputName={props.inputName} inputMembers={props.inputMembers}
				onMemberChange={props.onMemberChange}
				onDeleteGroup={props.onDeleteGroup}
				onInputNameChange={props.onInputNameChange}
				onInputMembersChange={props.onInputMembersChange}
				onAddGroup={props.onAddGroup}
				onAddMemberToAllGroups={props.onAddMemberToAllGroups}
				onChangeGroupToOn={props.onChangeGroupToOn}
				onChangeGroupToOff={props.onChangeGroupToOff}
				onChangeGroupToggle={props.onChangeGroupToggle}
				onDeleteMemberToAllGroups={props.onDeleteMemberToAllGroups} />
		</table>);
}

//export
window.MembersTable = MembersTable;