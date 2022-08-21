import React from 'react'

const ClientInfo = ({ client }) => {
  return (
    <span class="card-info">
      <div class="card-data">
        <div class="card-text">
          <div>
            Nome: {client.name}
            <br />
            Email: {client.email} <br />
          </div>
        </div>
      </div>
    </span>
  )
}

export default ClientInfo
